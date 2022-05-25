import * as Styles from './styles'

import { Input } from '@mui/material'
import { Button } from '@mui/material'
import ArrowDropUpIcon from '@mui/icons-material/ArrowDropUp'
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown'
import TextField from '@mui/material/TextField'
import { useState, useEffect } from 'react'
import { SendAndArchiveSharp } from '@mui/icons-material'
import jwt_decode from 'jwt-decode'
import ThumbUpIcon from '@mui/icons-material/ThumbUp'

export default function Post({ post, sendComment, sendLike }) {
  const [content, setContent] = useState('')
  const [token, setToken] = useState('')
  const [comments, setComments] = useState([])

  useEffect(() => {
    setToken(localStorage.getItem('token'))
  }, [])

  useEffect(() => {
    if (!post.comments) return
    setComments(post.comments)
  }, [post?.comments])

  function postNewComment(newComment) {
    const userInfo = jwt_decode(token)
    const comment = {
      content: newComment.comment,
      likes: [],
      ownerName: userInfo.email
    }

    setComments([...comments, comment])
    sendComment(newComment)
    setContent('')
  }

  function postNewLike(newLike) {
    const userInfo = jwt_decode(token)
    const like = {
      ...newLike,
      owner: userInfo.email
    }
    let newComment = comments

    const commentIndex = newComment.findIndex((item) => item._id === newLike.commentId)
    newLike.action
      ? newComment[commentIndex].likes.push({ isLiked: true, ownerEmail: like.email })
      : newComment[commentIndex].likes.splice(0, 1)

    setComments((oldState) => [...newComment])
    sendLike(newLike)
  }

  return (
    <Styles.Container>
      {post && (
        <>
          <Styles.Content>
            <Styles.Post>
              <Styles.RightColumn>
                <Styles.Title>{post.title}</Styles.Title>
                <Styles.Text>{post.content}</Styles.Text>
                <Styles.Badges>
                  {!!post.topic && post.topic.map((topic, index) => <Styles.Badge key={index}>{topic}</Styles.Badge>)}
                </Styles.Badges>
                <h3>{post.owner}</h3>
                <p className="date">{post.createdAt ? new Date(post.createdAt).toLocaleDateString() : '--/--/----'}</p>
              </Styles.RightColumn>
            </Styles.Post>
            {comments.map((comment, index) => {
              return (
                <Styles.Comments key={index}>
                  <Styles.LeftColumn>
                    <ArrowDropUpIcon
                      style={{
                        width: '100%',
                        height: '70px',
                        cursor: 'pointer'
                      }}
                      onClick={() =>
                        postNewLike({
                          postId: post._id,
                          commentId: comment._id,
                          action: true,
                          token
                        })
                      }
                      disabled={true}
                    />
                    {comment.likes ? comment.likes.length : 0}
                    <ArrowDropDownIcon
                      style={{
                        width: '100%',
                        height: '70px',
                        cursor: 'pointer'
                      }}
                      onClick={() =>
                        postNewLike({
                          postId: post._id,
                          commentId: comment._id,
                          action: false,
                          token
                        })
                      }
                    />
                  </Styles.LeftColumn>
                  {/* <Styles.Like>
                      <p>{comment.likes ? comment.likes.length : 0}</p>
                      <ThumbUpIcon
                        style={{
                          cursor: 'pointer',
                          filter: `${
                            comment.likes.find((commentLiked) => commentLiked.isLiked === true)
                              ? 'brightness(0.5)'
                              : 'brightness(1)'
                          }`
                        }}
                        onClick={() => postNewLike({ postId: comment._id, token })}
                        cursor="pointer"
                        />
                      </Styles.Like> */}
                  <h3>{comment.ownerName}:</h3>
                  <p>{comment.content}</p>
                </Styles.Comments>
              )
            })}
            <TextField
              style={{ backgroundColor: 'white', width: '100%', height: 'auto', margin: '24px 0px' }}
              required
              id="outlined-content"
              label="Comentar"
              variant="filled"
              multiline
              onChange={(e) => setContent(e.target.value)}
              value={content}
              minRows={3}
            />
            <Button
              variant="contained"
              style={{ marginBottom: '48px', width: '20%' }}
              onClick={() => postNewComment({ comment: content, token: token })}
            >
              Enviar
            </Button>
          </Styles.Content>
        </>
      )}
    </Styles.Container>
  )
}
