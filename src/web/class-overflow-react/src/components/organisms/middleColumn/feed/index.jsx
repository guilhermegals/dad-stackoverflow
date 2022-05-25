import * as Styles from './styles'
import { Button } from '@mui/material'
import TextField from '@mui/material/TextField'
import { TagsInput } from 'react-tag-input-component'
import { useState, useEffect } from 'react'
import Modal from 'react-modal'
import jwt_decode from 'jwt-decode'

export default function Feed({ posts, createPost }) {
  const [topic, setTopic] = useState([])
  const [title, setTitle] = useState('')
  const [token, setToken] = useState('')
  const [content, setContent] = useState('')
  const [modalIsOpen, setIsOpen] = useState(false)
  const [postsFeed, setPostsFeed] = useState([])

  useEffect(async () => {
    setToken(localStorage.getItem('token'))
  }, [])

  useEffect(() => {
    if (!posts) return
    setPostsFeed(posts)
  }, [posts])

  function openModal() {
    setIsOpen(true)
  }
  const customStyles = {
    content: {
      top: '50%',
      left: '50%',
      right: 'auto',
      bottom: 'auto',
      marginRight: '-50%',
      transform: 'translate(-50%, -50%)',
      backgroundColor: 'hsl(0deg 0% 18%)',
      borderRadius: '4px',
      width: '924px',
      height: '520px',
      padding: '24px',
      display: 'flex',
      color: 'var( --light-background)',
      flexDirection: 'column',
      justifyContent: 'center',
      alignItems: 'center',
      'border': '1px solid hsl(210deg 4% 26%)'
    },
    overlay: {
      background: 'rgb(37 37 37 / 67%)'
    }
  }

  async function publishNewPost(newPost) {
    const userInfo = jwt_decode(token)
    const post = {
      ...newPost,
      ownerName: userInfo.ownerEmail,
      // owner: 'Dbraz',
      comments: [],
      subject: ''
    }
    const response = await createPost(newPost)
    post._id = response
    setPostsFeed([post, ...postsFeed])
    closeModal()
    setTitle('')
    setContent('')
    setTopic([])
  }

  function afterOpenModal() {
    // references are now sync'd and can be accessed.
  }

  function closeModal() {
    setIsOpen(false)
  }
  return (
    <Styles.Container>
      <Styles.PostContent>
        <Modal
          isOpen={modalIsOpen}
          onAfterOpen={afterOpenModal}
          onRequestClose={closeModal}
          style={customStyles}
          ariaHideApp={false}
          contentLabel="Example Modal"
        >
          <h2 style={{ textAlign: 'left', marginTop: '0px' }}>Faça uma pergunta</h2>
          <TextField
            style={{ backgroundColor: 'white', width: '100%', height: 'auto', marginTop: '8px' }}
            required
            id="outlined-title"
            label="Título"
            placeholder="Ex: Estou com problemas na questão 3"
            variant="filled"
            onChange={(e) => setTitle(e.target.value)}
            value={title}
          />
          <TextField
            style={{ backgroundColor: 'white', width: '100%', height: 'auto', marginTop: '24px' }}
            required
            id="outlined-content"
            label="Descrição"
            placeholder="Ex: A questão 3 tem uma linguagem de programação que não conheço bem..."
            variant="filled"
            multiline
            onChange={(e) => setContent(e.target.value)}
            value={content}
            minRows={7}
          />
          <Styles.TagContent>
            <TagsInput
              value={topic}
              style={{ backgroundColor: 'white', width: '100%' }}
              onChange={setTopic}
              name="Tags"
              placeHolder="Inclua Tags"
            />
          </Styles.TagContent>
          <Button
            variant="contained"
            style={{ marginTop: '24px', width: '100%' }}
            onClick={() => publishNewPost({ token, title, content, topic })}
          >
            Publicar
          </Button>
        </Modal>
        <h1>Feed Questions</h1>
        <Button variant="contained" style={{ margin: '12px', height: '48px' }} onClick={openModal}>
          Fazer uma pergunta
        </Button>
      </Styles.PostContent>

      <Styles.Content>
        {postsFeed.map((post) => (
          <Styles.FeedPost key={post._id}>
            <Styles.LeftColumn>{post.comments ? post.comments.length : 0} comentários</Styles.LeftColumn>
            <Styles.RightColumn>
              <Styles.Title>
                <a href={`/post/${post._id}`}>{post.title}</a>
              </Styles.Title>
              <Styles.Badges>
                {post.topic && post.topic.map((topic, index) => <Styles.Badge key={index}>{topic}</Styles.Badge>)}
              </Styles.Badges>
              <h3>{post.ownerName}</h3>
              <p className="date">{post.createdAt ? new Date(post.createdAt).toLocaleDateString() : '--/--/----'}</p>
            </Styles.RightColumn>
          </Styles.FeedPost>
        ))}
      </Styles.Content>
    </Styles.Container>
  )
}
