import styled from 'styled-components'

export const Container = styled.div`
  width: 50%;
  height: 100vh;
  display: flex;
  justify-content: center;
  flex-direction: column;
  border-left: 1px solid hsl(210deg 4% 26%);
`
export const LeftColumn = styled.div`
  width: 150px;
  height: auto;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: var(--theme-dark-text);
  font-size: 1.5rem;
`
export const RightColumn = styled.div`
  padding-left: 24px;
  width: 100%;
  height: auto;
  color: hsl(206deg 100% 60%);
  h3 {
    color: hsl(210deg 11% 89%);
  }
  .date {
    font-weight: bold;
    color: hsl(210deg 11% 89%);
    font-size: 1rem;
  }
`
export const Badges = styled.div`
  display: flex;
  max-width: 300px;
  height: auto;
  justify-content: space-between;
`
export const Text = styled.div`
  width: 100%;
  height: auto;
  margin 40px 0;
  font-size: 1.2rem;
  color: var( --theme-light-text);
  word-wrap: break-word;
`

export const Badge = styled.div`
  padding: 14px;
  height: 40px;
  background-color: hsl(205deg 14% 28%);
  border-radius: 4px;
  display: flex;
  text-align: center;
  justify-content: center;
  align-items: center;
  color: hsl(205deg 47% 74%);
  font-size: 0.9rem;
`
export const Content = styled.div`
  padding: 20px 0px;
  height: 890px;
  width: 100%;
`

export const Like = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  p {
    padding-right: 24px;
  }
`
export const Comments = styled.div`
  display: flex;
  align-items: center;
  width: 100%;
  padding: 24px;
  border: 1px solid hsl(210deg 4% 26%);
  color: var(--theme-light-text);
  word-wrap: break-word;
  h3 {
    padding-left: 24px;
  }
  p {
    padding-left: 24px;
  }
`
export const Post = styled.div`
  display: flex;
  width: 100%;
  height: auto;
  padding: 20px;
  border: 1px solid hsl(210deg 4% 26%);
`

export const Title = styled.p`
  font-size: 1.5rem;
  word-wrap: break-word;
`

export const PublishPost = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 32px 32px 32px 32px;
  position: fixed;
  top: 50px;
  width: 916px;
  height: 120px;
  background-color: hsl(0deg 0% 22%);
`
